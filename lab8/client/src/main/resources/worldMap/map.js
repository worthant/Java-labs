var myMap;

ymaps.ready(init);

function init() {
    myMap = new ymaps.Map("map", {
        center: [55.76, 37.64],
        zoom: 2
    }, {
        searchControlProvider: 'yandex#search'
    });
}

function addCity(name, latitude, longitude, owner, iconName) {
    var placemark = new ymaps.Placemark([latitude, longitude], {
        hintContent: name,
        balloonContent: 'Owner: ' + owner
    }, {
        iconLayout: 'default#image',
        iconImageHref: '../icons/svg/' + iconName, 
        iconImageSize: [15, 15],
        iconImageOffset: [-24, -24],
        iconContentLayout: MyIconContentLayout
    });

    myMap.geoObjects.add(placemark);
}

var MyIconContentLayout = ymaps.templateLayoutFactory.createClass(
    '<div style="color: #FFFFFF; font-weight: bold;">$[properties.iconContent]</div>'
);