import formato_medicina from "../model/formato_medicina.js";

export const getFormato_medicinas = async (req, res) => {
    try {
        const formato_medicinas = await formato_medicina.findAll();
        res.json(formato_medicinas);
    }
    catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const getFormato_medicina = async (req, res) => {
    try {
        const formato_medicina = await ModelMedicina.findByPk(req.params.id);
        res.json(formato_medicina);
    }
    catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const createFormato_medicina = async (req, res) => {
    try {
        const formato_medicina = await ModelMedicina.create(req.body);
        res.json(formato_medicina);
    }
    catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const updateFormato_medicina = async (req, res) => {
    try {
        const formato_medicina = await ModelMedicina.findByPk(req.params.id);
        if (!formato_medicina) {
            return res.status(404).json({
                message: "Error",
                error: "Formato de medicina no encontrado"
            });
        }
        await formato_medicina.update(req.body);
        res.json(formato_medicina);
    }
    catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

export const deleteFormato_medicina = async (req, res) => {
    try {
        const formato_medicina = await ModelMedicina.findByPk(req.params.id);
        if (!formato_medicina) {
            return res.status(404).json({
                message: "Error",
                error: "Formato de medicina no encontrado"
            });
        }
        await formato_medicina.destroy();
        res.json({
            message: "Formato de medicina eliminado"
        });
    }
    catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}
