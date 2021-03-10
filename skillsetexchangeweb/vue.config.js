module.exports = {
  devServer: {
	  port: 8080,
	proxy: {
      '^/api': {
        //target: 'http://42.3.28.133:8081',
		target: 'http://localhost:8081',
        ws: true,
        changeOrigin: true,
		secure:false,
    pathRewrite: {'^/api': '/api'},
    logLevel: 'debug' ,
      },
  },
}
};