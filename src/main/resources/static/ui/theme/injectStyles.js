import React from "react"

export default function withStyles(Component, styles) {
    return function StyledComponent(props) {
        const classes = styles()
        return <Component {...props} classes={ classes }/>
    }
}