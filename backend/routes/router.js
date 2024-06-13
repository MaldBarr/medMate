import express from 'express';
//Usuario
import {iniciarSesion,registerUsuario} from '../controller/usercontroller.js';
//Formato
import { obtenerIdFormato_medicina,obtenerNombreFormato_medicina } from '../controller/formato_medicinaController.js';
//Medicamentos
import { getMedicamentos,obtenerIdMedicamento,obtenerNombreMedicamentoPorId } from '../controller/medicamentosController.js';
//Frecuencias
import { obtenerIdFrecuencia,obtenerNombreFrecuencia } from '../controller/frecuenciasController.js';
//horas
import { obtenerIdHora } from '../controller/horasController.js';
//recordatorios
import { createRecordatorio, getRecordatoriosByUserId ,updateRecordatorio, deleteRecordatorio} from '../controller/recordatorioController.js';
//HorasMedicas
import { getHorasMedicasByID, createHoraMedica, updateHoraMedica, deleteHoraMedica } from '../controller/HoraMedicaController.js';

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

//obtener Nombres
router.post('/obtenerNombreMedicamentoPorId', obtenerNombreMedicamentoPorId);
router.post('/obtenerNombreFormato_medicina', obtenerNombreFormato_medicina);
router.post('/obtenerNombreFrecuencia', obtenerNombreFrecuencia);



//update
router.post('/updateRecordatorio', updateRecordatorio);

//delete
router.post('/deleteRecordatorio', deleteRecordatorio);

//obtener nombre
router.post('/obtenerNombreMedicamentoPorId', obtenerNombreMedicamentoPorId);

//HorasMedicas
router.post('/getHorasMedicasByID', getHorasMedicasByID);
router.post('/createHoraMedica', createHoraMedica);
router.post('/updateHoraMedica', updateHoraMedica);
router.post('/deleteHoraMedica', deleteHoraMedica);

export default router;
