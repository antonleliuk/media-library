import React, {useEffect} from 'react';
import {Link as RouterLink, useLocation} from 'react-router-dom';
import PropTypes from 'prop-types';
import {Avatar, Box, Divider, Drawer, Hidden, List, makeStyles, Typography} from '@material-ui/core';
import {
  AlertCircle as AlertCircleIcon,
  BarChart as BarChartIcon,
  Book as Library,
  Settings as SettingsIcon,
  Users as UsersIcon,
  Video,
  Music,
  BookOpen
} from 'react-feather';
import NavItem from './NavItem';

const user = {
  avatar: '/static/images/avatars/avatar_6.png',
  name: 'Anton Leliuk'
};

const items = [
  {
    href: '/home',
    icon: BarChartIcon,
    title: 'Dashboard'
  },
  {
    href: '/users',
    icon: UsersIcon,
    title: 'Users'
  },
  {
    href: '/library',
    icon: Library,
    title: 'Library',
    children: [
      {
        href: '/library/video',
        icon: Video,
        title: 'Video'
      },
      {
        href: '/library/audio',
        icon: Music,
        title: 'Music'
      },
      {
        href: '/library/book',
        icon: BookOpen,
        title: 'Books'
      }
    ]
  },
  {
    href: '/settings',
    icon: SettingsIcon,
    title: 'Settings'
  },
  {
    href: '/404',
    icon: AlertCircleIcon,
    title: 'Error'
  }
];

const useStyles = makeStyles(() => ({
  mobileDrawer: {
    width: 256
  },
  desktopDrawer: {
    width: 256,
    top: 64,
    height: 'calc(100% - 64px)'
  },
  avatar: {
    cursor: 'pointer',
    width: 64,
    height: 64
  }
}));

const NavBar = ({ onMobileClose, openMobile }) => {
  const classes = useStyles();
  const location = useLocation();

  useEffect(() => {
    if (openMobile && onMobileClose) {
      onMobileClose();
    }
  }, [location.pathname]);

  const content = (
    <Box
      height="100%"
      display="flex"
      flexDirection="column"
    >
      <Box
        alignItems="center"
        display="flex"
        flexDirection="column"
        p={2}
      >
        <Avatar
          className={classes.avatar}
          component={RouterLink}
          src={user.avatar}
          to="/profile/my"
        />
        <Typography
          className={classes.name}
          color="textPrimary"
          variant="h5"
        >
          {user.name}
        </Typography>
      </Box>
      <Divider />
      <Box p={2}>
        <List>
          {items.map((item) => (
            <NavItem
              href={item.href}
              key={item.title}
              title={item.title}
              icon={item.icon}
              children={item.children}
            />
          ))}
        </List>
      </Box>
    </Box>
  );

  return (
    <>
      <Hidden lgUp>
        <Drawer
          anchor="left"
          classes={{ paper: classes.mobileDrawer }}
          onClose={onMobileClose}
          open={openMobile}
          variant="temporary"
        >
          {content}
        </Drawer>
      </Hidden>
      <Hidden mdDown>
        <Drawer
          anchor="left"
          classes={{ paper: classes.desktopDrawer }}
          open
          variant="persistent"
        >
          {content}
        </Drawer>
      </Hidden>
    </>
  );
};

NavBar.propTypes = {
  onMobileClose: PropTypes.func,
  openMobile: PropTypes.bool
};

NavBar.defaultProps = {
  onMobileClose: () => {},
  openMobile: false
};

export default NavBar;
