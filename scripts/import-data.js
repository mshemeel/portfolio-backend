const { MongoClient } = require('mongodb');
const fs = require('fs');
const path = require('path');
const dotenv = require('dotenv');
const config = require('./config');

// Load environment variables from .env file
dotenv.config({ path: path.join(__dirname, '../.env') });

// MongoDB Atlas connection string from config
const uri = config.mongodb.getConnectionString();

// Path to JSON data files
const DATA_DIR = path.join(__dirname, '../../shemeel-portfolio-web-src/src/data');

// Connect to MongoDB
async function connectToMongoDB() {
  const client = new MongoClient(uri);
  
  try {
    await client.connect();
    console.log('Connected to MongoDB Atlas');
    return client.db('portfolio_db');
  } catch (error) {
    console.error('Error connecting to MongoDB:', error);
    throw error;
  }
}

// Read JSON data from file
function readJsonFile(filePath) {
  try {
    const data = fs.readFileSync(filePath, 'utf8');
    return JSON.parse(data);
  } catch (error) {
    console.error(`Error reading file ${filePath}:`, error);
    throw error;
  }
}

// Create collections and import data
async function importData() {
  let client;
  
  try {
    // Connect to MongoDB
    client = new MongoClient(uri);
    await client.connect();
    console.log('Connected to MongoDB Atlas');
    
    const db = client.db('portfolio_db');
    
    // Import header data
    const headerData = readJsonFile(path.join(DATA_DIR, 'header.json'));
    await importCollection(db, 'header', headerData);
    
    // Import footer data
    const footerData = readJsonFile(path.join(DATA_DIR, 'footer.json'));
    await importCollection(db, 'footer', footerData);
    
    // Import hero data
    const heroData = readJsonFile(path.join(DATA_DIR, 'hero.json'));
    await importCollection(db, 'hero', heroData);
    
    // Import about data
    const aboutData = readJsonFile(path.join(DATA_DIR, 'about.json'));
    await importCollection(db, 'about', aboutData);
    
    // Import experience data
    const experienceData = readJsonFile(path.join(DATA_DIR, 'experience.json'));
    if (experienceData.experienceData) {
      // Add order field to experience items
      const experienceItems = experienceData.experienceData.map((item, index) => ({
        ...item,
        order: index + 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'experiences', experienceItems);
    }
    
    // Import skills and skillCategories
    const skillsData = readJsonFile(path.join(DATA_DIR, 'skills.json'));
    
    if (skillsData.skillCategories) {
      const skillCategories = skillsData.skillCategories.map((category, index) => ({
        ...category,
        order: index + 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'skillCategories', skillCategories);
    }
    
    if (skillsData.skillsData) {
      const skills = skillsData.skillsData.map(skill => ({
        ...skill,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'skills', skills);
    }
    
    // Import education and certifications
    const educationData = readJsonFile(path.join(DATA_DIR, 'education.json'));
    
    if (educationData.educationData) {
      const educationItems = educationData.educationData.map((item, index) => ({
        ...item,
        order: index + 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'education', educationItems);
    }
    
    if (educationData.certificationsData) {
      const certifications = educationData.certificationsData.map((cert, index) => ({
        ...cert,
        order: index + 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'certifications', certifications);
    }
    
    // Import projects and project categories
    const projectsData = readJsonFile(path.join(DATA_DIR, 'projects.json'));
    
    if (projectsData.categories) {
      const projectCategories = projectsData.categories.map((category, index) => ({
        ...category,
        order: index + 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'projectCategories', projectCategories);
    }
    
    if (projectsData.projectsData) {
      const projects = projectsData.projectsData.map((project, index) => ({
        ...project,
        order: index + 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'projects', projects);
    }
    
    // Import testimonials
    const testimonialsData = readJsonFile(path.join(DATA_DIR, 'testimonials.json'));
    
    if (testimonialsData.testimonials) {
      const testimonials = testimonialsData.testimonials.map((testimonial, index) => ({
        ...testimonial,
        order: index + 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }));
      
      await importArrayCollection(db, 'testimonials', testimonials);
    }
    
    // Import contact data
    const contactData = readJsonFile(path.join(DATA_DIR, 'contact.json'));
    await importCollection(db, 'contact', {
      ...contactData,
      createdAt: new Date(),
      updatedAt: new Date()
    });
    
    // Import metadata
    const metadataData = readJsonFile(path.join(DATA_DIR, 'metadata.json'));
    await importCollection(db, 'metadata', {
      ...metadataData,
      createdAt: new Date(),
      updatedAt: new Date()
    });
    
    // Create default profile
    const profileData = {
      name: "Muhammed Shemeel",
      title: "Full Stack Developer",
      email: "mshemeel007@gmail.com",
      location: "Dubai, UAE",
      avatarUrl: "/images/shemeel-profile.jpeg",
      bio: aboutData.description || "Full Stack Developer specializing in Java and React applications",
      resumeUrl: aboutData.resumeUrl || "/resume.pdf",
      createdAt: new Date(),
      updatedAt: new Date()
    };
    
    await importCollection(db, 'profiles', profileData);
    
    // Create indexes
    await createIndexes(db);
    
    console.log('All data imported successfully!');
    
  } catch (error) {
    console.error('Error importing data:', error);
  } finally {
    if (client) {
      await client.close();
      console.log('MongoDB connection closed');
    }
  }
}

// Import single document to collection
async function importCollection(db, collectionName, data) {
  try {
    const collection = db.collection(collectionName);
    
    // Check if collection has any documents
    const count = await collection.countDocuments();
    
    if (count > 0) {
      console.log(`Collection ${collectionName} already has data. Updating...`);
      await collection.deleteMany({});
    }
    
    await collection.insertOne(data);
    console.log(`Imported data into ${collectionName} collection`);
  } catch (error) {
    console.error(`Error importing data to ${collectionName}:`, error);
    throw error;
  }
}

// Import array of documents to collection
async function importArrayCollection(db, collectionName, dataArray) {
  try {
    const collection = db.collection(collectionName);
    
    // Check if collection has any documents
    const count = await collection.countDocuments();
    
    if (count > 0) {
      console.log(`Collection ${collectionName} already has data. Updating...`);
      await collection.deleteMany({});
    }
    
    if (dataArray.length > 0) {
      await collection.insertMany(dataArray);
      console.log(`Imported ${dataArray.length} documents into ${collectionName} collection`);
    }
  } catch (error) {
    console.error(`Error importing data to ${collectionName}:`, error);
    throw error;
  }
}

// Create indexes for collections
async function createIndexes(db) {
  try {
    // Users collection
    await db.collection('users').createIndex({ email: 1 }, { unique: true });
    await db.collection('users').createIndex({ username: 1 }, { unique: true });
    
    // Experiences collection
    await db.collection('experiences').createIndex({ order: 1 });
    
    // Skills collection
    await db.collection('skills').createIndex({ category: 1 });
    await db.collection('skills').createIndex({ level: -1 });
    
    // Projects collection
    await db.collection('projects').createIndex({ category: 1 });
    await db.collection('projects').createIndex({ featured: 1 });
    await db.collection('projects').createIndex({ order: 1 });
    
    // ContactFormSubmissions collection
    await db.collection('contactFormSubmissions').createIndex({ status: 1 });
    await db.collection('contactFormSubmissions').createIndex({ createdAt: -1 });
    
    console.log('All indexes created successfully');
  } catch (error) {
    console.error('Error creating indexes:', error);
    throw error;
  }
}

// Run the import
importData().catch(console.error); 