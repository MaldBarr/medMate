import db from "../database/db.js";
import { DataTypes } from "sequelize";

const horas = db.define('horas', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    hora: {
        type: DataTypes.STRING,
    },
    createdAt: {
        type: DataTypes.DATE,
    },
    updatedAt: {
        type: DataTypes.DATE,
    }
    
},{ tableName: 'horas', // Reemplaza con el nombre real de tu tabla
});

export default horas;
