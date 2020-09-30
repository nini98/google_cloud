import React from 'react';
import styles from './header.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const header = () => {

    return (
        <div className={cx("header")} id="header">
            <img className={cx("header-img")} src="/img/header.jpg" alt={"header-img"} />
        </div>
    );
};

export default header;