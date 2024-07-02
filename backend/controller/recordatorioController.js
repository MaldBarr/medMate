import Recordatorio from "../model/recordatorioModel.js";

export const getRecordatorios = async (req, res) => {
    try {
        const recordatorios = await Recordatorio.findAll();
        res.json(recordatorios);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const createRecordatorio = async (req, res) => {
    try {
        const { id_usuario, id_medicamento, id_formato,id_frecuencia,dosis,cantidad,hora } = req.body;
        const recordatorio = await Recordatorio.create({ id_usuario, id_medicamento, id_formato,id_frecuencia,dosis,cantidad,hora });
        res.json(recordatorio);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const updateRecordatorio = async (req, res) => {
    try {
        const { id } = req.body;
        const recordatorio = await Recordatorio.findByPk(id);
        if (!recordatorio) {
            return res.status(404).json({
                message: "Error",
                error: "Recordatorio no encontrado"
            });
        }
        const { id_medicamento, id_formato, id_frecuencia } = req.body;
        await recordatorio.update({ id_medicamento, id_formato, id_frecuencia });
        res.json(recordatorio);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const deleteRecordatorio = async (req, res) => {
    try {
        const recordatorio = await Recordatorio.findByPk(req.body.id);
        if (!recordatorio) {
            return res.status(404).json({
                message: "Error",
                error: "Recordatorio no encontrado"
            });
        }
        await recordatorio.destroy();
        res.json({ message: "Recordatorio eliminado" });
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const getRecordatoriosByUserId = async (req, res) => {
    try {
        const { id_usuario } = req.body;
        const recordatorios = await Recordatorio.findAll({
            where: { id_usuario }
        });
        res.json(recordatorios);
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}
