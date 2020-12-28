/* eslint-disable */
const webpack = require('webpack');
const path = require('path');
const ManifestPlugin = require('webpack-manifest-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const config = {
    entry: {
        login : "./src/main/resources/static/ui/pages/login/index.js"
    },
    output: {
        path: path.resolve(__dirname, 'src/main/resources/static/dist'),
        filename: '[name].[hash].js'
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
            filename: 'styles.[hash].css',
            chunkFilename: 'styles.[hash].css'
        }),
        new ManifestPlugin()
    ],
    // optimization: {
    //     runtimeChunk: 'single',
    //     splitChunks: {
    //         cacheGroups: {
    //             vendor: {
    //                 test: /[\\/]node_modules[\\/]/,
    //                 name: 'vendors',
    //                 chunks: 'all'
    //             }
    //         }
    //     }
    // }
};

module.exports = (env, argv) => {
    if (argv.hot) {
        // Cannot use 'contenthash' when hot reloading is enabled.
        config.output.filename = '[name].[hash].js';
    }

    return config;
};