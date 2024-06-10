import express from 'express';
//Usuario
import {iniciarSesion,registerUsuario} from '../controller/usercontroller.js';
//Formato
import { obtenerIdFormato_medicina } from '../controller/formato_medicinaController.js';
//Medicamentos
import { getMedicamentos,obtenerIdMedicamento,obtenerNombreMedicamentoPorId } from '../controller/medicamentosController.js';
//Frecuencias
import { obtenerIdFrecuencia } from '../controller/frecuenciasController.js';
//horas
import { obtenerIdHora } from '../controller/horasController.js';
//recordatirios
import { createRecordatorio, getRecordatoriosByUserId ,updateRecordatorio, deleteRecordatorio} from '../controller/recordatorioController.js';


const router = express.Router();

/*router.get('/', (req, res) => {
    res.send('Hello World');
});*/

router.post('/iniciarSesion', iniciarSesion);
router.post('/registerUsuario', registerUsuario);
router.get('/getMedicamentos', getMedicamentos);
router.post('/createRecordatorio', createRecordatorio);
router.post('/getRecordatoriosByUserId', getRecordatoriosByUserId);


//obetener ids
router.post('/obtenerIdMedicamento', obtenerIdMedicamento);
router.post('/obtenerIdFormato_medicina', obtenerIdFormato_medicina);
router.post('/obtenerIdFrecuencia', obtenerIdFrecuencia);
router.post('/obtenerIdHora', obtenerIdHora);


//update
router.post('/updateRecordatorio', updateRecordatorio);

//delete
router.post('/deleteRecordatorio', deleteRecordatorio);

//obtener nombre
router.post('/obtenerNombreMedicamentoPorId', obtenerNombreMedicamentoPorId);

export default router;
