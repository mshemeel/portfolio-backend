# MongoDB Database Structure

This document outlines the MongoDB database structure for the portfolio application backend.

## Database Name: `portfolio_db`

## Collections

### 1. `users`

Stores admin user information for the CMS.

```javascript
{
  _id: ObjectId,
  username: String,
  email: String,
  passwordHash: String,
  role: String,  // "ADMIN" or "EDITOR"
  firstName: String,
  lastName: String,
  createdAt: Date,
  updatedAt: Date,
  lastLoginAt: Date
}
```

### 2. `profiles`

Stores the main profile information.

```javascript
{
  _id: ObjectId,
  name: String,
  title: String,
  email: String,
  location: String,
  avatarUrl: String,
  bio: String,
  resumeUrl: String,
  createdAt: Date,
  updatedAt: Date
}
```

### 3. `header`

Stores header configuration.

```javascript
{
  _id: ObjectId,
  name: String,
  logoLink: String,
  navItems: [
    {
      id: String,
      label: String,
      href: String
    }
  ],
  createdAt: Date,
  updatedAt: Date
}
```

### 4. `footer`

Stores footer configuration.

```javascript
{
  _id: ObjectId,
  name: String,
  title: String,
  socialLinks: [
    {
      id: String,
      url: String,
      ariaLabel: String
    }
  ],
  navItems: [
    {
      id: String,
      label: String,
      href: String
    }
  ],
  copyright: {
    text: String
  },
  createdAt: Date,
  updatedAt: Date
}
```

### 5. `hero`

Stores hero section data.

```javascript
{
  _id: ObjectId,
  greeting: String,
  name: String,
  titles: [String],
  description: String,
  ctaButtons: [
    {
      label: String,
      href: String,
      isPrimary: Boolean
    }
  ],
  createdAt: Date,
  updatedAt: Date
}
```

### 6. `about`

Stores about section data.

```javascript
{
  _id: ObjectId,
  title: String,
  description: String,
  highlightedTechnologies: [String],
  statistics: [
    {
      value: String,
      label: String
    }
  ],
  resumeUrl: String,
  createdAt: Date,
  updatedAt: Date
}
```

### 7. `experiences`

Stores work experience data.

```javascript
{
  _id: ObjectId,
  company: String,
  logoUrl: String,
  websiteUrl: String,
  role: String,
  period: String,
  location: String,
  type: String,
  description: String,
  technologies: [String],
  responsibilities: [String],
  achievements: [String],
  createdAt: Date,
  updatedAt: Date,
  order: Number  // For controlling display order
}
```

### 8. `skills`

Stores skills data.

```javascript
{
  _id: ObjectId,
  name: String,
  level: Number,
  category: String,
  logoPath: String,
  createdAt: Date,
  updatedAt: Date
}
```

### 9. `skillCategories`

Stores skill category data.

```javascript
{
  _id: ObjectId,
  id: String,
  name: String,
  order: Number,
  createdAt: Date,
  updatedAt: Date
}
```

### 10. `education`

Stores education history.

```javascript
{
  _id: ObjectId,
  institution: String,
  degree: String,
  field: String,
  period: String,
  location: String,
  logo: String,
  createdAt: Date,
  updatedAt: Date,
  order: Number  // For controlling display order
}
```

### 11. `certifications`

Stores certification data.

```javascript
{
  _id: ObjectId,
  name: String,
  issuer: String,
  date: String,
  description: String,
  credentialUrl: String,
  createdAt: Date,
  updatedAt: Date,
  order: Number  // For controlling display order
}
```

### 12. `projects`

Stores project data.

```javascript
{
  _id: ObjectId,
  title: String,
  description: String,
  image: String,
  copyright: String,
  technologies: [String],
  category: String,
  link: String,
  featured: Boolean,
  createdAt: Date,
  updatedAt: Date,
  order: Number  // For controlling display order
}
```

### 13. `projectCategories`

Stores project category data.

```javascript
{
  _id: ObjectId,
  id: String,
  name: String,
  order: Number,
  createdAt: Date,
  updatedAt: Date
}
```

### 14. `testimonials`

Stores testimonial data.

```javascript
{
  _id: ObjectId,
  name: String,
  position: String,
  date: String,
  relationship: String,
  recommendation: String,
  profileImage: String,
  linkedInUrl: String,
  createdAt: Date,
  updatedAt: Date,
  order: Number  // For controlling display order
}
```

### 15. `contact`

Stores contact section data.

```javascript
{
  _id: ObjectId,
  title: String,
  description: String,
  email: String,
  phone: String,
  location: String,
  createdAt: Date,
  updatedAt: Date
}
```

### 16. `contactFormSubmissions`

Stores contact form submissions.

```javascript
{
  _id: ObjectId,
  name: String,
  email: String,
  subject: String,
  message: String,
  status: String,  // "NEW", "READ", "REPLIED", "ARCHIVED"
  ipAddress: String,
  createdAt: Date,
  updatedAt: Date
}
```

### 17. `metadata`

Stores SEO metadata.

```javascript
{
  _id: ObjectId,
  title: String,
  description: String,
  keywords: [String],
  author: String,
  openGraph: {
    title: String,
    description: String,
    image: String,
    url: String
  },
  twitter: {
    card: String,
    title: String,
    description: String,
    image: String
  },
  createdAt: Date,
  updatedAt: Date
}
```

## Indexes

To optimize query performance:

```javascript
// Users collection
db.users.createIndex({ email: 1 }, { unique: true })
db.users.createIndex({ username: 1 }, { unique: true })

// Experiences collection
db.experiences.createIndex({ order: 1 })

// Skills collection
db.skills.createIndex({ category: 1 })
db.skills.createIndex({ level: -1 })

// Projects collection
db.projects.createIndex({ category: 1 })
db.projects.createIndex({ featured: 1 })
db.projects.createIndex({ order: 1 })

// ContactFormSubmissions collection
db.contactFormSubmissions.createIndex({ status: 1 })
db.contactFormSubmissions.createIndex({ createdAt: -1 })
```

## Data Validation

MongoDB schema validation will be implemented to ensure data integrity:

```javascript
db.createCollection("skills", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["name", "level", "category"],
      properties: {
        name: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        level: {
          bsonType: "int",
          minimum: 1,
          maximum: 100,
          description: "must be an integer between 1 and 100 and is required"
        },
        category: {
          bsonType: "string",
          description: "must be a string and is required"
        }
      }
    }
  }
})
```

Similar validation will be added for other collections as needed.

## Data Relationships

The database uses a denormalized approach for most collections to optimize read performance. However, some references are maintained:

- Projects reference projectCategories by their `id` field
- Skills reference skillCategories by their `id` field
- Education and Experience items are ordered by the `order` field

## Versioning and Backup Strategy

- Full database backups will be performed daily
- Incremental backups will be performed hourly
- Database changes will be versioned through change tracking in application code
- Point-in-time recovery will be enabled

## Monitoring and Alerts

- Database performance metrics will be monitored using MongoDB Atlas
- Alerts will be set up for:
  - High CPU/Memory usage
  - Slow queries (>100ms)
  - Disk space usage exceeding 80%
  - Failed connection attempts 