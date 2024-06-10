import db from "../database/db.js";
import { DataTypes } from "sequelize";

const frecuencias = db.define('frecuencias', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    frecuencia: {
        type: DataTypes.STRING,
    },
    createdAt: {
        type: DataTypes.DATE,
    },
    updatedAt: {
        type: DataTypes.DATE,
    }
    
},{ tableName: 'frecuencias', // Reemplaza con el nombre real de tu tabla
});

export default frecuencias;
