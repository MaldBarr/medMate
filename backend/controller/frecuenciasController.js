import frecuencias from "../model/frecuenciaModel.js";

export const obtenerIdFrecuencia = async (req, res) => {
    try {
        const frecuencia = await frecuencias.findOne({
            where: {
                frecuencia: req.body.frecuencia
            }
        });
        res.json({id:frecuencia.id});
    } catch (error) {
        res.status(500).json({
            message: "Error en el servidor"
        });
    }
}

export const obtenerNombreFrecuencia = async (req, res) => {
    try {
        const frecuencia = await frecuencias.findOne({
            where: {
                id: req.body.id
            }
        });
        res.json({nombre: frecuencia.frecuencia});
    } catch (error) {
        res.status(500).json({
            message: "Error en el servidor"
        });
    }
}
