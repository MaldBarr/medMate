import db from "../database/db.js";
import { DataTypes } from "sequelize";

const medicamentos = db.define('medicamentos', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    medicamento: {
        type: DataTypes.STRING,
    },
    createdAt: {
        type: DataTypes.DATE,
    },
    updatedAt: {
        type: DataTypes.DATE,
    }
    
},{ tableName: 'medicamentos', // Reemplaza con el nombre real de tu tabla
});

export default medicamentos;
