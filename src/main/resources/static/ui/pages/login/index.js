import {mobxApplication} from "../../mobx/application"
import * as React from "react"
import LoginStore from "./store/LoginStore"
import {configure} from "mobx"
import LoginPage from "./components/LoginPage";
import theme from "../../theme"
import {ThemeProvider} from "@material-ui/core"
import GlobalStyles from "../../theme/GlobalStyles"

configure({
    enforceActions: "always",
    isolateGlobalState: true
})

const appManager = {
    createMainStore: () => new LoginStore()
}

@mobxApplication(appManager)
export default class LoginPageIndex extends React.Component {
    render() {
        return <ThemeProvider theme={theme}>
            <GlobalStyles />
            <LoginPage/>
        </ThemeProvider>
    }
}