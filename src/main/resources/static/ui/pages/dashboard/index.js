import {mobxApplication} from "../../mobx/application"
import * as React from "react"

import {configure} from "mobx"

configure({
    enforceActions: "always",
    isolateGlobalState: true
})