/**
 * Start a local MongoDB instance using Docker
 * This script provides a local MongoDB instance for development and testing
 */
const { exec } = require('child_process');
const fs = require('fs');
const path = require('path');

// Create data directory if it doesn't exist
const dataDir = path.join(__dirname, '../mongo-data');
if (!fs.existsSync(dataDir)) {
  fs.mkdirSync(dataDir, { recursive: true });
  console.log(`Created MongoDB data directory: ${dataDir}`);
}

// Docker command to start MongoDB
const dockerCommand = `docker run -d --name portfolio-mongodb \
  -p 27017:27017 \
  -v ${dataDir}:/data/db \
  -e MONGO_INITDB_DATABASE=portfolio_db \
  mongo:latest`;

// Run the Docker command
exec(dockerCommand, (error, stdout, stderr) => {
  if (error) {
    console.error(`Error starting MongoDB container: ${error.message}`);
    
    // Check if the container already exists
    if (error.message.includes('Conflict. The container name')) {
      console.log('MongoDB container already exists. Starting existing container...');
      
      exec('docker start portfolio-mongodb', (startError, startStdout) => {
        if (startError) {
          console.error(`Error starting existing MongoDB container: ${startError.message}`);
          return;
        }
        console.log('Existing MongoDB container started successfully.');
        console.log('MongoDB is now running at: mongodb://localhost:27017/portfolio_db');
      });
    }
    return;
  }
  
  if (stderr) {
    console.error(`stderr: ${stderr}`);
    return;
  }
  
  console.log('MongoDB container started successfully.');
  console.log('Container ID:', stdout.trim());
  console.log('MongoDB is now running at: mongodb://localhost:27017/portfolio_db');
  console.log('\nTo use this local MongoDB instance, run:');
  console.log('MONGODB_URI=mongodb://localhost:27017/portfolio_db npm run import-data');
}); 