# Portfolio Backend

This directory contains the backend code and scripts for the portfolio website. The backend is built with Java, Spring Boot, and MongoDB.

## MongoDB Data Import

The `scripts` directory contains utilities to import the initial data from the frontend JSON files into MongoDB Atlas.

### Prerequisites

1. **MongoDB Atlas Account** (Option 1 - Cloud MongoDB)
   - Create a free account at [MongoDB Atlas](https://www.mongodb.com/cloud/atlas/register)
   - Set up a new cluster (the free tier is sufficient)
   - Create a database user with read/write permissions
   - Whitelist your IP address in the Network Access settings

   **OR**

   **Docker** (Option 2 - Local MongoDB)
   - Have Docker installed on your system
   - No MongoDB Atlas account required

2. **Node.js and npm**
   - Make sure you have Node.js installed (version 14+ recommended)

### Setup

1. **Install Dependencies**

```bash
cd backend
npm install
```

2. **Configure MongoDB Connection**

#### Option 1: Using MongoDB Atlas

Copy the example environment file and update it with your MongoDB Atlas credentials:

```bash
cp .env-example .env
```

Edit the `.env` file with your MongoDB Atlas credentials:
- `MONGO_USERNAME`: Your MongoDB Atlas username
- `MONGO_PASSWORD`: Your MongoDB Atlas password
- `MONGO_CLUSTER_URL`: Your MongoDB Atlas cluster URL (without the mongodb+srv:// prefix)

Alternatively, you can directly edit the `scripts/config.js` file.

#### Option 2: Using Local MongoDB with Docker

Instead of setting up MongoDB Atlas, you can run MongoDB locally using Docker:

```bash
npm run start-local-mongodb
```

This will start a MongoDB container that can be used for development and testing.

### Import Data

#### Using MongoDB Atlas:

```bash
npm run import-data
```

#### Using Local MongoDB:

```bash
npm run import-to-local
```

The import script will:
1. Connect to your MongoDB instance (Atlas or local)
2. Create the necessary collections
3. Import data from the JSON files in the `src/data` directory
4. Set up proper indexes for performance optimization

### Verification

After running the import script, you should see success messages for each collection. 

#### For MongoDB Atlas:
You can verify the data has been imported correctly by checking your MongoDB Atlas dashboard:

1. Log in to [MongoDB Atlas](https://cloud.mongodb.com)
2. Navigate to your cluster
3. Click on "Collections" tab
4. Explore the `portfolio_db` database and check that all collections have data

#### For Local MongoDB:
You can use MongoDB Compass to connect to your local MongoDB instance:

1. Download and install [MongoDB Compass](https://www.mongodb.com/products/compass)
2. Connect to `mongodb://localhost:27017`
3. Explore the `portfolio_db` database

## Spring Boot Backend

The Spring Boot backend application will be developed in this directory. The API documentation can be found in `API-Spec.md` and the database structure in `DB-Structure.md`. 