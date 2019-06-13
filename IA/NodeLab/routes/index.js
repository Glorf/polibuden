var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  if(req.session.login) {
    var db = req.db;
    var notes = db.get('notes');
    notes.find({username: req.session.login}, {}, function (e, docs) {
      res.render('index', {title: 'Notatki', user: req.session.login, entries: docs});
    });
  } else {
    res.redirect('/login')
  }
});

router.post('/', function(req, res) {
  if(!req.body["note"] ||
      req.body["note"].length === 0 ||
      !req.session.login)
    res.redirect('/');
  else {
    var db = req.db;
    var notes = db.get('notes');
    notes.insert({
      username: req.session.login,
      note: req.body["note"],
      time: new Date()
    }, function (err, doc) {
      if(err) {
        res.send("Error!");
      }
      else {
        res.redirect('/')
      }
    });
  }
});

//router.



module.exports = router;
