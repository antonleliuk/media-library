import * as React from "react"
import {makeStyles} from "@material-ui/core"
import withStyles from "../../../theme/injectStyles"
import {inject, observer} from "mobx-react"

const useStyles = makeStyles((theme) => ({
    root: {
        backgroundColor: theme.palette.background.dark,
        display: 'flex',
        height: '100%',
        overflow: 'hidden',
        width: '100%'
    },
    wrapper: {
        display: 'flex',
        flex: '1 1 auto',
        overflow: 'hidden',
        paddingTop: 64,
        [theme.breakpoints.up('lg')]: {
            paddingLeft: 256
        }
    },
    contentContainer: {
        display: 'flex',
        flex: '1 1 auto',
        overflow: 'hidden'
    },
    content: {
        flex: '1 1 auto',
        height: '100%',
        overflow: 'auto'
    }
}))

@inject("store", "fetchApi")
@observer
class HomePage extends React.Component {
    render() {
        return (
            "Hello Home"
        )
    }
}

export default withStyles(HomePage, useStyles)