import React from "react"
import ReactPropTypes from "prop-types"
import {observer, PropTypes} from "mobx-react"
import TextField from "@material-ui/core/TextField"
import {action} from "mobx"
import ErrorMessageContainer from "../../error/ErrorMessageContainer"
import FormValue from "../../model/FormValue"

@observer
export default class Text extends React.Component {
    static propTypes = {
        type: ReactPropTypes.string,
        property: ReactPropTypes.string.isRequired,
        name: ReactPropTypes.string.isRequired,
        required: ReactPropTypes.bool,
        value: ReactPropTypes.instanceOf(FormValue).isRequired,
        errorMessage: ReactPropTypes.instanceOf(ErrorMessageContainer).isRequired
    }

    static defaultProps = {
        type: 'text',
        required: false
    }

    render() {
        const { type, property, name, required, value, errorMessage } = this.props
        return <TextField name={property}
                          required={required}
                          label={name}
                          type={ type }
                          autoFocus={true}
                          variant='outlined'
                          fullWidth={true}
                          margin='normal'
                          value={ value.value }
                          error={ errorMessage.hasError(property) }
                          helperText={ errorMessage.getFieldError(property) }
                          onChange={action((e) => value.value = e.target.value )}/>
    }
}