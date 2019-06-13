var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
    res.render('login', {title: 'Notatki', nowy: req.session.nowy});
});

router.post('/', function(req, res) {
    req.session.login = req.body['login'];
    res.redirect('/')
});

//router.



module.exports = router;
