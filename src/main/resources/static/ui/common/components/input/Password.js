import Text from "./Text"
import ReactPropTypes from "prop-types"
import {PropTypes} from "mobx-react"
import ErrorMessageContainer from "../../error/ErrorMessageContainer"

export default class Password extends Text {
    static propTypes = {
        type: ReactPropTypes.string,
        property: ReactPropTypes.string.isRequired,
        name: ReactPropTypes.string.isRequired,
        required: ReactPropTypes.bool,
        value: PropTypes.observableObject,
        errorMessage: ReactPropTypes.instanceOf(ErrorMessageContainer).isRequired
    }

    static defaultProps = {
        type: 'password',
        required: false
    }
}