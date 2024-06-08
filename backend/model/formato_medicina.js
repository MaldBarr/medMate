import db from "../database/db.js";
import { DataTypes } from "sequelize";

const formato_medicina = db.define('formato_medicina', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    formato: {
        type: DataTypes.STRING,
    },
    createdAt: {
        type: DataTypes.DATE,
    },
    updatedAt: {
        type: DataTypes.DATE,
    }
    
},{ tableName: 'formato_medicina', // Reemplaza con el nombre real de tu tabla
});

export default formato_medicina;
