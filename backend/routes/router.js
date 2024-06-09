import express from 'express';
import {iniciarSesion,registerUsuario} from '../controller/usercontroller.js';
import { getFormato_medicinas } from '../controller/formato_medicinaController.js';
import { getMedicamentos } from '../controller/medicamentosController.js';

const router = express.Router();

/*router.get('/', (req, res) => {
    res.send('Hello World');
});*/

router.post('/iniciarSesion', iniciarSesion);
router.post('/registerUsuario', registerUsuario);
router.get('/getFormato_medicinas', getFormato_medicinas);
router.get('/getMedicamentos', getMedicamentos);

export default router;
