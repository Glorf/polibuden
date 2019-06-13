const request = require('request-promise');
const jsdom = require("jsdom");
const { JSDOM } = jsdom;

var productList = [];

class Product {
    constructor(name, price, threads, clock) {
        this.name = name;
        this.price = price;
        this.threads = threads;
        this.clock = clock;
    }

    calculate () {
        this.eurPerMhz = this.price/(this.clock*this.threads)
    }
}

var reNum = RegExp("[0-9,.]+");

function parseProduct(prodUrl) {
    request.get(prodUrl).then(response => {
        let dom = new JSDOM(response).window.document;
        let processor = new Product();

        processor.price = Number(reNum.exec(dom.querySelector(".pprice").innerHTML.replace(".","").replace(",","."))[0]);

        let params = dom.querySelectorAll("#tTechdaten+div tbody tr");
        //console.log(params);

        let mark = "";
        let name = "";

        for(let i=0; i<params.length; i++) {
            let kv = params[i].cells;
            let key = kv.item(0).innerHTML;
            if(key.match("^(Prozessor Modell:|Prozessor:)$")) {
                //console.log(params[i].innerHTML);
                name = kv.item(1).innerHTML;
            }
            else if(key.match("^(Prozessor Serie:|Prozessorfamilie:)$")) {
                mark = kv.item(1).innerHTML.replace("®", "");
            }
            else if(key.match("^(Prozessortakt:|Prozessor-Taktfrequenz:)$")) {
                processor.clock = Number(reNum.exec(kv.item(1).innerHTML)[0].replace(",", "."));
            }
            else if(key.match("^(Anzahl der Threads:|Prozessor-Threads:)$")) {
                processor.threads = Number(reNum.exec(kv.item(1).innerHTML)[0]);
            }
        }

        processor.name = (mark + " " + name).replace(new RegExp("[^\x00-\x7F]"), " ");
        if(processor.name != null & processor.price != null && processor.clock != null && processor.threads != null) {
            processor.calculate();

            productList.push(processor);
            console.log(JSON.stringify(processor));
        }
        else {
            console.log("OMMITED")
        }

    }).catch(error => {
        console.log(error);
    })
}

function getListPage(url) {
    request.get(url).then(response => {
        dom = new JSDOM(response).window.document;

        products = dom.querySelectorAll("#bProducts .pcontent .phover-complete-link");
        for(let i=0; i<products.length; i++) {
            parseProduct(products[i].getAttribute("href"));
        }

        nextPage = dom.querySelector("a[aria-label$=\"chste Seite\"]");
        if(nextPage != null) {
            getListPage(nextPage.getAttribute("href"));
        }
        else {
            setTimeout(resultShow, 10000);
        }

    }).catch(error => {
        console.log(error);
    })
}

function resultShow() {
    console.log("Lista procesorów według malejącej opłacalności EUR/GHz");
    productList.sort((a, b) => a.eurPerMhz - b.eurPerMhz);

    for(let i = 0; i<productList.length; i++) {
        let p = productList[i];
        console.log(p.name+"\t\t\t"+p.eurPerMhz);
    }
}

getListPage("https://www.mindfactory.de/Hardware/Prozessoren+(CPU).html");