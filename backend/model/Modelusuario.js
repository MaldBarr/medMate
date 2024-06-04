import db from "../database/db.js";

import { DataTypes } from "sequelize";

db.define("usuario", {
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        allowNull: false,
        primaryKey: true
    },
    name: {
        type: DataTypes.STRING,
    },
    email: {
        type: DataTypes.STRING,
    },
    contrasenia: {
        type: DataTypes.STRING,
    }
});

export default db.models.usuario;