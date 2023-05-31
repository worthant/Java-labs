ymaps.ready(init);

function init () {
    var myMap = new ymaps.Map('map', {
            center: [55.76, 37.64],
            zoom: 3
        }, {
            searchControlProvider: 'yandex#search'
        }),
        objectManager = new ymaps.ObjectManager({

        });

    // Set the icon for individual objects
    objectManager.objects.options.set('iconLayout', 'default#image');
    objectManager.objects.options.set('iconImageHref', '../icons/building.svg');  // Set this to the path of your image
    objectManager.objects.options.set('iconImageSize', [15, 15]);  // You can adjust this to the size of your icon

    objectManager.objects.options.set('iconColor', 'rgba(255,106,31,0.89)');
    myMap.geoObjects.add(objectManager);

    $.ajax({
        url: "data.json"
    }).done(function(data) {
        objectManager.add(data);
    });

}