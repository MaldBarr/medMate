import { Sequelize } from "sequelize";

const db = new Sequelize(
    'neondb',           // Nombre de la base de datos
    'neondb_owner',     // Usuario de la base de datos
    'KoNRcnO30abs',     // Contraseña del usuario
    {
        host: 'ep-lucky-cake-a4p308l5.us-east-1.aws.neon.tech',  // Host de la base de datos
        dialect: 'postgres',
        dialectOptions: {
            ssl: {
                require: true,            // Fuerza SSL
                rejectUnauthorized: false // Desactiva la verificación del certificado
            }
        }
    }
);

export default db;
