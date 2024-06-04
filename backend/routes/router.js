import express from 'express';
import {iniciarSesion} from '../controller/usercontroller.js';

const router = express.Router();

/*router.get('/', (req, res) => {
    res.send('Hello World');
});*/

router.post('/iniciarSesion', iniciarSesion);

export default router;
