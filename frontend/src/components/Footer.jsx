import styles from '../styles/footer.module.css';
import iconFacebook from '../assets/icons/icon facebook.svg';
import iconTwitter from '../assets/icons/icon tweet.svg';
import iconLinkedin from '../assets/icons/icon linkedin.svg';
import iconInstagram from '../assets/icons/icon ig.svg';

export default function Footer() {
	return (
		<footer className={styles.footer}>
			<div className={styles.footer__copyright}>©2022 Digital Booking</div>

			<div className={styles.footer__social}>
				<a href='https://www.facebook.com/' target='_blank' rel='noreferrer'>
					<img src={iconFacebook} alt='' />
				</a>
				<a
					className='social__linkedin'
					href='https://www.linkedin.com/'
					target='_blank'
					rel='noreferrer'
				>
					<img className='' src={iconLinkedin}></img>
				</a>
				<a
					className='social__twitter'
					href='https://www.twitter.com/'
					target='_blank'
					rel='noreferrer'
				>
					<img className='' src={iconTwitter}></img>
				</a>

				<a
					className='social__instagram'
					href='https://www.instagram.com/'
					target='_blank'
					rel='noreferrer'
				>
					<img className='' src={iconInstagram}></img>
				</a>
			</div>
		</footer>
	);
}
