var webpack = require("webpack");

module.exports = {
  entry: './src/js/index.js',
  output: {
    filename: 'index_bundle.js'
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
      'windows.jQuery': 'jquery',
    })
  ]
}
