//Quitar los logs ....

console.log("Service worker running!");

const CACHE_NAME = 'v1';

self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(CACHE_NAME)
            .then(cache => {
                fetch('asset-manifest.json')
                .then(response => {
                    if (response.ok) {
                        response.json().then(manifest => {
                            const urls = Object.keys(manifest).map(key => manifest[key]);
                            urls.push('/');
                            urls.push('/assets/icon.png');
                            cache.addAll(urls);
                        });
                    }
                })
            })
    );
});

self.addEventListener('fetch', event => {
    event.respondWith(
        caches.match(event.request).then(response => {
            return response || fetch(event.request);
        })
    );
});

self.addEventListener('activate', event => {
    event.waitUntil(
        caches.keys().then(keyList => {
            Promise.all(
                keyList.map(key => {
                    if (key !== CACHE_NAME) {
                        return caches.delete(key);
                    }
            } ));

        })
    );
} );


/*
self.addEventListener('install', function() {
    //console.log('Install!');
});

self.addEventListener('activate', function() {
    console.log('Activate!');
});

self.addEventListener('fetch', function(event) {
    console.log('Fetch!', event.request);
});

*/