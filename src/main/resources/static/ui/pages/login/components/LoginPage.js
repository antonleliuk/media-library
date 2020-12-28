import {inject, observer} from "mobx-react"
import React from "react"
import Container from "@material-ui/core/Container"
import Button from "@material-ui/core/Button"
import {Box, makeStyles, Typography} from "@material-ui/core"
import Page from "../../../common/components/layout/Page"
import withStyles from "../../../theme/injectStyles"
import Text from "../../../common/components/input/Text"
import Password from "../../../common/components/input/Password"

const useStyles = makeStyles((theme) => ({
    root: {
        backgroundColor: theme.palette.background.dark,
        height: '100%',
        paddingBottom: theme.spacing(3),
        paddingTop: theme.spacing(3)
    }
}))

@inject("store", "fetchApi")
@observer
class LoginPage extends React.Component {
    render() {
        const { store, fetchApi, classes } = this.props
        return <Page
            className={classes.root}
            title="Login">
            <Box
                display="flex"
                flexDirection="column"
                height="100%"
                justifyContent="center">
                <Container maxWidth='sm'>
                    <form noValidate={true} autoComplete={'off'}>
                        <Box mb={3}>
                            <Typography color="textPrimary" variant="h2" align={"center"}>
                                Sign in
                            </Typography>
                        </Box>
                        <Text
                            name='Username'
                            property='login'
                            value={ store.login }
                            required={ true }
                            errorMessage={ store.messageContainer }
                        />

                        <Password
                            name='Password'
                            property='password'
                            value={ store.password }
                            required={ true }
                            errorMessage={ store.messageContainer }
                        />

                        <Box my={2}>
                            <Button
                                color="primary"
                                fullWidth
                                size="large"
                                type="button"
                                onClick={ () => {store.loginUser(fetchApi)} }
                                variant="contained">
                                Sign in
                            </Button>
                        </Box>
                    </form>
                </Container>
            </Box>
        </Page>
    }
}

export default withStyles(LoginPage, useStyles)