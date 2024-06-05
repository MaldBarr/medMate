import express from 'express';
import {iniciarSesion,registerUsuario} from '../controller/usercontroller.js';

const router = express.Router();

/*router.get('/', (req, res) => {
    res.send('Hello World');
});*/

router.post('/iniciarSesion', iniciarSesion);
router.post('/registerUsuario', registerUsuario);

export default router;
