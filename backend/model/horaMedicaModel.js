import db from "../database/db.js";
import { DataTypes } from "sequelize";

const horaMedica = db.define('horaMedica', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false
    },
    id_usuario: {
        type: DataTypes.STRING,
    },
    nombre: {
        type: DataTypes.STRING,
        allowNull: false
    },
    fecha: {
        type: DataTypes.DATE,
        allowNull: false
    },
    id_hora: {
        type: DataTypes.STRING,
        allowNull: false
    },
    id_minuto: {
        type: DataTypes.STRING,
        allowNull: false
    },
    createdAt: {
        type: DataTypes.DATE,
    },
    updatedAt: {
        type: DataTypes.DATE,
    }
}, {
    timestamps: false,
    tableName: 'recordatorio_hora_medica'
})

export default horaMedica;