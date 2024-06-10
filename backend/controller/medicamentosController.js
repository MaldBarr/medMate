import medicamentos from "../model/Modelmedicamentos.js";

export const getMedicamentos = async (req, res) => {
    try {
        const medicamentosList = await medicamentos.findAll();
        res.json(medicamentosList);
    } catch (error) {
        res.status(500).json({
            message: "Error en el servidor"
        });
    }
}

export const obtenerIdMedicamento = async (req, res) => {
    try {
        const medicamento = await medicamentos.findOne({
            where: {
                medicamento: req.body.medicamento
            }
        });
        res.json({id:medicamento.id});
    } catch (error) {
        res.status(500).json({
            message: "Error en el servidor"
        });
    }
}


export const obtenerNombreMedicamentoPorId = async (req, res) => {
    try {
        const medicamento = await medicamentos.findOne({
            where: {
                id: req.body.id
            }
        });
        res.json({ nombre: medicamento.medicamento });
    } catch (error) {
        res.status(500).json({
            message: "Error en el servidor"
        });
    }
}

