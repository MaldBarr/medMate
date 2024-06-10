import db from "../database/db.js";
import { DataTypes } from "sequelize";

const Recordatorio = db.define('recordatorio', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    id_usuario: {
        type: DataTypes.STRING,
    },
    id_medicamento: {
        type: DataTypes.STRING,
    },
    id_formato: {
        type: DataTypes.STRING,
    },
    id_frecuencia: {
        type: DataTypes.STRING,
    },
    id_hora: {
        type: DataTypes.STRING,
    },
    createdAt: {
        type: DataTypes.DATE,
    },
    updatedAt: {
        type: DataTypes.DATE,
    }
}, {
    tableName: 'recordatorio', // Reemplaza con el nombre real de tu tabla
});

export default Recordatorio;
