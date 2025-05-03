# API Specification for Portfolio Backend

This document outlines the RESTful API endpoints for the portfolio application backend.

## Base URL

```
Production: https://api.shemeel-portfolio.com/api/v1
Development: http://localhost:8080/api/v1
```

## Authentication

All API endpoints are secured using JWT authentication except for the public endpoints.

### Authentication Headers

```
Authorization: Bearer {jwt_token}
```

## Endpoints

### User Profile

#### Get Profile Information

```
GET /profile
```

Response:
```json
{
  "name": "Muhammed Shemeel",
  "title": "Full Stack Developer",
  "email": "mshemeel007@gmail.com",
  "location": "UAE",
  "avatarUrl": "/images/shemeel-profile.jpeg"
}
```

### Header Section

#### Get Header Data

```
GET /header
```

Response:
```json
{
  "name": "Muhammed Shemeel",
  "logoLink": "/",
  "navItems": [
    {
      "id": "about",
      "label": "About",
      "href": "#about"
    },
    {
      "id": "experience",
      "label": "Experience",
      "href": "#experience"
    }
    // Additional navigation items...
  ]
}
```

### Footer Section

#### Get Footer Data

```
GET /footer
```

Response:
```json
{
  "name": "Muhammed Shemeel",
  "title": "Full Stack Developer",
  "socialLinks": [
    {
      "id": "linkedin",
      "url": "https://www.linkedin.com/in/mshemeel007/",
      "ariaLabel": "LinkedIn"
    },
    {
      "id": "github",
      "url": "https://github.com/mshemeel",
      "ariaLabel": "GitHub"
    },
    {
      "id": "email",
      "url": "mailto:mshemeel007@gmail.com",
      "ariaLabel": "Email"
    }
  ],
  "navItems": [
    {
      "id": "about",
      "label": "About",
      "href": "#about"
    }
    // Additional navigation items...
  ],
  "copyright": {
    "text": "All rights reserved."
  }
}
```

### Hero Section

#### Get Hero Data

```
GET /hero
```

Response:
```json
{
  "greeting": "Hi there, I'm",
  "name": "Muhammed Shemeel",
  "titles": ["Full Stack Developer", "Java Expert", "Cloud Enthusiast"],
  "description": "I specialize in building robust and scalable applications with Java, Spring Boot, and cloud technologies.",
  "ctaButtons": [
    {
      "label": "Contact Me",
      "href": "#contact",
      "isPrimary": true
    },
    {
      "label": "Download CV",
      "href": "/resume.pdf",
      "isPrimary": false
    }
  ]
}
```

### About Section

#### Get About Data

```
GET /about
```

Response:
```json
{
  "title": "About Me",
  "description": "I'm a passionate Full Stack Developer with over 7 years of experience in developing enterprise applications...",
  "highlightedTechnologies": ["Java", "Spring Boot", "Microservices", "React"],
  "statistics": [
    {
      "value": "7+",
      "label": "Years Experience"
    },
    {
      "value": "20+",
      "label": "Projects Completed"
    },
    {
      "value": "5+",
      "label": "Companies Worked"
    }
  ],
  "resumeUrl": "/resume.pdf"
}
```

### Experience Section

#### Get Experience Data

```
GET /experiences
```

Response:
```json
{
  "experienceData": [
    {
      "id": 1,
      "company": "Network International",
      "logoUrl": "/images/companies/logo-network.png",
      "websiteUrl": "https://www.network.ae/",
      "role": "Senior Software Engineer",
      "period": "Jul 2023 - Present",
      "location": "Dubai, UAE",
      "type": "Full-time",
      "description": "Working on mobile payments solutions, including Apple Tap to Pay integration...",
      "technologies": ["Java", "Spring Boot", "Microservices", "React Native", "Swift", "Kotlin"],
      "responsibilities": [
        "Design, develop, and maintain mobile payment applications",
        "Collaborate with cross-functional teams to deliver high-quality software"
      ]
    }
    // Additional experience items...
  ]
}
```

### Skills Section

#### Get Skills Data

```
GET /skills
```

Response:
```json
{
  "skillCategories": [
    { "id": "all", "name": "All Skills" },
    { "id": "backend", "name": "Backend" },
    { "id": "frontend", "name": "Frontend" }
    // Additional categories...
  ],
  "skillsData": [
    { 
      "name": "Java", 
      "level": 95, 
      "category": "backend", 
      "logoPath": "/images/skills/java.svg"
    }
    // Additional skills...
  ]
}
```

### Education Section

#### Get Education Data

```
GET /education
```

Response:
```json
{
  "educationData": [
    {
      "id": 1,
      "institution": "University of Calicut",
      "degree": "Bachelor of Technology (B.Tech)",
      "field": "Electronics and Communication Engineering",
      "period": "Jun 2012 - Jun 2016",
      "location": "Kerala, India",
      "logo": "🏛️"
    }
  ],
  "certificationsData": [
    {
      "id": 1,
      "name": "Team Champ Award",
      "issuer": "IBS Software",
      "date": "Awarded for exceptional team contribution"
    }
    // Additional certifications...
  ]
}
```

### Projects Section

#### Get Projects Data

```
GET /projects
```

Response:
```json
{
  "projectsData": [
    {
      "id": 1,
      "title": "n-Genius One SoftPOS",
      "description": "Mobile payment solution with Apple Tap to Pay integration...",
      "image": "/projects/n-genius-one-attp.png",
      "copyright": "Image © Network International",
      "technologies": ["Java", "Spring Boot", "Microservices","React Native", "Swift", "Kotlin"],
      "category": "mobile",
      "link": "https://www.network.ae/en/press-and-media/network-international-launches-tap-to-pay-on-iphone-for-uae-merchants",
      "featured": true
    }
    // Additional projects...
  ],
  "categories": [
    { "id": "all", "name": "All Projects" }
    // Additional categories...
  ]
}
```

### Testimonials Section

#### Get Testimonials Data

```
GET /testimonials
```

Response:
```json
{
  "testimonials": [
    {
      "id": 1,
      "name": "Jijo George",
      "position": "Project Lead at Cognizant",
      "date": "July 31, 2023",
      "relationship": "Jijo was senior to Muhammed but didn't manage Muhammed directly",
      "recommendation": "I have had the pleasure of working alongside Muhammed Shemeel...",
      "profileImage": "/testimonials/jijo-george.jpg",
      "linkedInUrl": "https://www.linkedin.com/in/jijopg4u/"
    }
    // Additional testimonials...
  ]
}
```

### Contact Section

#### Get Contact Data

```
GET /contact
```

Response:
```json
{
  "title": "Get In Touch",
  "description": "Feel free to reach out if you want to collaborate on a project, have a question, or just want to connect.",
  "email": "mshemeel007@gmail.com",
  "phone": "+971 XXX XXX XXX",
  "location": "Dubai, UAE"
}
```

#### Submit Contact Form

```
POST /contact/submit
```

Request:
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "subject": "Project Collaboration",
  "message": "I'd like to discuss a potential project with you..."
}
```

Response:
```json
{
  "success": true,
  "message": "Thank you for your message. I'll get back to you soon!"
}
```

### Metadata

#### Get SEO Metadata

```
GET /metadata
```

Response:
```json
{
  "title": "Muhammed Shemeel | Full Stack Developer",
  "description": "Portfolio of Muhammed Shemeel, a Full Stack Developer specializing in Java, Spring Boot, and React applications.",
  "keywords": ["full stack developer", "java developer", "spring boot", "react", "portfolio"],
  "author": "Muhammed Shemeel",
  "openGraph": {
    "title": "Muhammed Shemeel | Full Stack Developer",
    "description": "Portfolio of Muhammed Shemeel, a Full Stack Developer specializing in Java, Spring Boot, and React applications.",
    "image": "/images/og-image.jpg",
    "url": "https://shemeel-portfolio.com"
  },
  "twitter": {
    "card": "summary_large_image",
    "title": "Muhammed Shemeel | Full Stack Developer",
    "description": "Portfolio of Muhammed Shemeel, a Full Stack Developer specializing in Java, Spring Boot, and React applications.",
    "image": "/images/twitter-image.jpg"
  }
}
```

## Error Responses

All endpoints will return appropriate HTTP status codes:

- `200 OK`: Request successful
- `201 Created`: Resource created successfully
- `400 Bad Request`: Invalid input
- `401 Unauthorized`: Authentication required
- `403 Forbidden`: Authenticated but not authorized
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server-side error

Error Response Format:
```json
{
  "timestamp": "2023-09-05T14:22:45.123Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid input data",
  "path": "/api/v1/contact/submit"
}
```

## Rate Limiting

API requests are limited to 100 requests per IP address per hour.

## Caching

Responses from GET endpoints are cached for 5 minutes to improve performance. 