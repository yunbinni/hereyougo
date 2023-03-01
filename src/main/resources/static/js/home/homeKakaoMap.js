navigator.geolocation.getCurrentPosition((pos) => {

    var mapContainer = document.getElementById('map');

    var mapOption = {
        center: new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption);
});