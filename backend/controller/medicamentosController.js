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
