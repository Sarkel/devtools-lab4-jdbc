# Configuration
1. Configuration file should be placed in `src/main/resources`
2. Configuration file should be named `config.properties`
3. Configuration file properties:
   - db.host
   - db.port
   - db.database
   - db.user
   - db.password

# Database
1. Database is by default running on MySQL. Code changes are needed to support other database (Not Recommended).
2. Server details are stored in configuration files (look at # Configuration)
3. DB setup is stored in `src/main/resources/db_script.sql`
   - script is loaded automatically while on app start
