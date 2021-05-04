import {mobxApplication} from "../../mobx/application"
import * as React from "react"
import HomeStore from "./store/HomeStore"
import HomePage from "./components/HomePage"
import Layout from "../../layout"
import {BrowserRouter, Route} from "react-router-dom"

const appManager = {
    createMainStore: () => new HomeStore()
}

@mobxApplication(appManager)
export default class HomePageIndex extends React.Component {
    render() {
        return <BrowserRouter>
            <Layout>
                <Route path={'/home'}>
                    <HomePage/>
                </Route>
            </Layout>
        </BrowserRouter>
    }
}