import {inject, observer, Provider} from "mobx-react"
import React from "react"
import ReactDOM from "react-dom"
import FetchService from "../rest/FetchService"

function getMobxAppHoc (WrappedComponent, appManager) {
    let ObservedComponent = observer(WrappedComponent)
    ObservedComponent = inject("store", "fetchApi")(ObservedComponent)

    class MobxApp extends React.Component {
        render() {
            return <Provider store={this.props.store} fetchApi={this.props.fetchApi}>
                    <ObservedComponent {...this.props}/>
                </Provider>
        }
    }

    const displayName = WrappedComponent.displayName || WrappedComponent.name || "Component"
    MobxApp.displayName = `MobxApp(${displayName})`
    return MobxApp
}

export function mobxApplication(appManager) {
    return function(WrappedComponent){
        const MobxApp = getMobxAppHoc(WrappedComponent, appManager)

        const store = appManager.createMainStore()
        const fetchApi = new FetchService()
        ReactDOM.render(<MobxApp store={store} fetchApi={fetchApi}/>, document.getElementById("application"))
    }
}