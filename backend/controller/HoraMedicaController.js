import HoraMedica from '../model/horaMedicaModel.js';

export const getHorasMedicas = async (req, res) => {
    try {
        const horasMedicas = await HoraMedica.findAll();
        res.json(horasMedicas);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const createHoraMedica = async (req, res) => {
    try {
        const { id_usuario, nombre, fecha, id_hora, id_minuto } = req.body;
        const horaMedica = await HoraMedica.create({ id_usuario, nombre, fecha, id_hora, id_minuto });
        res.json(horaMedica);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const updateHoraMedica = async (req, res) => {
    try {
        const { id } = req.body;
        const horaMedica = await HoraMedica.findByPk(id);
        if (!horaMedica) {
            return res.status(404).json({
                message: "Error",
                error: "Hora Medica no encontrada"
            });
        }
        const { nombre, fecha, id_hora, id_minuto } = req.body;
        await horaMedica.update({ nombre, fecha, id_hora, id_minuto });
        res.json(horaMedica);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}