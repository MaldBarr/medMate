import horas from "../model/horaModel.js";

export const obtenerIdHora = async (req, res) => {
    try {
        const hora = await horas.findOne({
            where: {
                hora: req.body.hora
            }
        });
        res.json(hora.id);
    } catch (error) {
        res.status(500).json({
            message: "Error en el servidor"
        });
    }
}