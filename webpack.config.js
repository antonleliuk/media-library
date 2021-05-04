/* eslint-disable */
const path = require('path');
const { WebpackManifestPlugin } = require('webpack-manifest-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const config = {
    entry: {
        'login' : "./src/main/resources/static/ui/pages/login/index.js",
        'home': "./src/main/resources/static/ui/pages/home/index.js",
        'not-found': './src/main/resources/static/ui/pages/errors/not-found.js',
        'access-denied': './src/main/resources/static/ui/pages/errors/access-denied.js',
        'error': './src/main/resources/static/ui/pages/errors/error.js',
    },
    output: {
        path: path.resolve(__dirname, 'src/main/resources/static/dist'),
        filename: '[name].[chunkhash].js',
        publicPath: ''
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                use: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/i,
                use: [ MiniCssExtractPlugin.loader, 'css-loader'],
            }
        ]
    },
    resolve: {
        extensions: [
            '.js',
            '.jsx'
        ]
    },
    devServer: {
        contentBase: './src/main/resources/static/dist'
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: 'styles.[fullhash].css',
            chunkFilename: 'styles.[fullhash].css'
        }),
        new WebpackManifestPlugin()
    ],
    // optimization: {
    //     splitChunks: {
    //         chunks: 'all',
    //         cacheGroups: {
    //             vendor: {
    //                 test: /[\\/]node_modules[\\/]/,
    //                 name: 'vendors',
    //                 chunks: 'all',
    //                 filename: '[name].[fullhash].js'
    //             }
    //         }
    //     }
    // }
};

module.exports = (env, argv) => {
    if (argv.hot) {
        // Cannot use 'contenthash' when hot reloading is enabled.
        config.output.filename = '[name].[fullhash].js';
    }


    return config;
};