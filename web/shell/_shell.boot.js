  /* 外壳启动：在 <head> 里就定好这一页是「壳」还是「内容页」，
     免得内容先闪一下再被外壳盖住。?pane=car / ?pane=music 都是内容页。 */
  (function () {
    var d = document.documentElement;
    d.className = (d.className ? d.className + ' ' : '')
      + (location.search.indexOf('pane=') >= 0 ? 'pane' : 'shell');
  })();
