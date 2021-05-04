import React, {useState} from 'react'
import { NavLink as RouterLink } from 'react-router-dom'
import clsx from 'clsx'
import PropTypes from 'prop-types'
import {
  Button, Collapse, List,
  ListItem,
  makeStyles
} from '@material-ui/core'
import {ExpandLess, ExpandMore} from "@material-ui/icons"

const useStyles = makeStyles((theme) => ({
  item: {
    display: 'flex',
    paddingTop: 0,
    paddingBottom: 0
  },
  button: {
    color: theme.palette.text.secondary,
    fontWeight: theme.typography.fontWeightMedium,
    justifyContent: 'flex-start',
    letterSpacing: 0,
    padding: '10px 8px',
    textTransform: 'none',
    width: '100%'
  },
  icon: {
    marginRight: theme.spacing(1)
  },
  title: {
    marginRight: 'auto'
  },
  active: {
    color: theme.palette.primary.main,
    '& $title': {
      fontWeight: theme.typography.fontWeightMedium
    },
    '& $icon': {
      color: theme.palette.primary.main
    }
  },
  nested: {
    paddingLeft: theme.spacing(4),
  }
}))

const NavItem = ({
  className,
  href,
  icon: Icon,
  title,
  children,
  ...rest
}) => {
  const classes = useStyles()
  const [open, setOpen] = useState()

  const handleOpen = () => {
    setOpen(!open)
  }

  return (
      <React.Fragment>
        <ListItem
            className={clsx(classes.item, className)}
            disableGutters
            {...rest}
        >
          <Button
              activeClassName={classes.active}
              className={classes.button}
              component={RouterLink}
              onClick={ handleOpen }
              to={href}
          >
            {Icon && (
                <Icon
                    className={classes.icon}
                    size="20"
                />
            )}
            <span className={classes.title}>
              {title}
            </span>
            {children && (open ? <ExpandLess/> : <ExpandMore/>)}
          </Button>
        </ListItem>
        {children && <Collapse in={ open } timeout={'auto'} unmountOnExit={true}>
          <List component={'div'} disablePadding={true}>
          {
              children.map( (child) => (
                  <NavItem
                      href={child.href}
                      icon={child.icon}
                      title={child.title}
                      key={child.title}
                      className={ classes.nested }/>
              ))
          }
          </List>
        </Collapse>}
      </React.Fragment>
  )
}

NavItem.propTypes = {
  className: PropTypes.string,
  href: PropTypes.string,
  icon: PropTypes.elementType,
  title: PropTypes.string,
  children: PropTypes.array
}

export default NavItem
