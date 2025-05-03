/**
 * MongoDB Atlas Configuration
 * 
 * Replace the placeholders with your actual MongoDB Atlas credentials:
 * - MONGO_USERNAME: Your MongoDB Atlas username
 * - MONGO_PASSWORD: Your MongoDB Atlas password
 * - MONGO_CLUSTER_URL: Your MongoDB Atlas cluster URL (without the mongodb+srv:// prefix)
 */

module.exports = {
  // MongoDB Atlas connection settings
  mongodb: {
    username: process.env.MONGO_USERNAME || '<your-username>',
    password: process.env.MONGO_PASSWORD || '<your-password>',
    clusterUrl: process.env.MONGO_CLUSTER_URL || '<your-cluster-url>',
    dbName: 'portfolio_db',
    
    // Direct connection string (takes precedence if provided)
    directUri: process.env.MONGODB_URI,
    
    // Construct the full connection string
    getConnectionString: function() {
      if (this.directUri) {
        return this.directUri;
      }
      return `mongodb+srv://${this.username}:${this.password}@${this.clusterUrl}/${this.dbName}?retryWrites=true&w=majority`;
    }
  }
}; 