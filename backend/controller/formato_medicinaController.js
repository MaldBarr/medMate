import Formato_medicina from "../model/formato_medicina.js";


export const obtenerIdFormato_medicina = async (req, res) => {
    try {
        const formato_medicina = await Formato_medicina.findOne({
            where: {
                formato: req.body.formato
            }
        });
        res.json({ id: formato_medicina.id });
    } catch (error) {
        res.status(500).json({
            message: "Error",
            error: error.message
        });
    }
}

