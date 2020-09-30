import React from 'react';
import styles from './footer.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const Footer = () => (
  <footer className={cx('footer')}>
    <div className={cx('content')}>© 2020 Copyright - Blog(OOO)</div>
  </footer>
);

export default Footer;