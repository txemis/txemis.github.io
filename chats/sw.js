self.addEventListener('install', e => {
 e.waitUntil(
   // after the service worker is installed,
   // open a new cache
   caches.open('my-pwa-cache').then(cache => {
     // add all URLs of resources we want to cache
     return cache.addAll([
       '/',
       '/chats/intergram.html',
       '/chats/about.html',
       '/images/livechat.jpg',
       '/styles/main.min.css',
       '/scripts/main.min.js',
     ]);
   })
 );
});
