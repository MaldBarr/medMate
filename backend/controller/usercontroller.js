import Modelusuario from "../model/Modelusuario.js";

export const getUsuarios = async (req, res) => {
    try {
        const usuarios = await Modelusuario.findAll();
        res.json(usuarios);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const getUsuario = async (req, res) => {
    try {
        const usuario = await Modelusuario.findByPk(req.params.id);
        res.json(usuario);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const createUsuario = async (req, res) => {
    try {
        const usuario = await Modelusuario.create(req.body);
        res.json(usuario);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const updateUsuario = async (req, res) => {
    try {
        const usuario = await Modelusuario.findByPk(req.params.id);
        await usuario.update(req.body);
        res.json(usuario);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const deleteUsuario = async (req, res) => {
    try {
        const usuario = await Modelusuario.findByPk(req.params.id);
        await usuario.destroy();
        res.json(usuario);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const iniciarSesion = async (req, res) => {
    try {
        const usuario = await Modelusuario.findOne({
            where: {
                email: req.body.email,
                contrasenia: req.body.contrasenia
            }
        });
        if (usuario) {
            res.json(true);
        } else {
            res.status(404).json({
                message: "Error",
                error: "Usuario no encontrado"
            });
        }
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}